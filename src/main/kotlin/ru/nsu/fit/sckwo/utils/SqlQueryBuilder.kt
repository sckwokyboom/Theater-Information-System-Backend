package ru.nsu.fit.sckwo.utils

class SqlQueryBuilder {
    private var selectClause: String? = null
    private var updateClause: String? = null
    private var insertIntoClause: String? = null
    private var setClause: String? = null
    private var valuesClause: String? = null
    private var isDeleteQuery: Boolean = false
    private var fromClause: String? = null
    private val joinClauses: MutableList<String> = ArrayList()
    private val whereClauses: MutableList<String> = ArrayList()
    private lateinit var currentQueryType: QueryType


    enum class QueryType {
        SELECT,
        SELECT_DISTINCT,
        INSERT_INTO,
        UPDATE,
        DELETE
    }

    @JvmRecord
    data class SqlQueryBuilderJoin(
        private val sqlQueryBuilder: SqlQueryBuilder,
        private val joinType: String,
        private val tableName: String,
    ) {
        fun on(onClause: String): SqlQueryBuilder {
            val joinClause = "$joinType JOIN $tableName ON $onClause"
            sqlQueryBuilder.joinClauses.add(joinClause)
            return sqlQueryBuilder
        }
    }

    @JvmRecord
    data class SqlQueryBuilderUpdate(private val sqlQueryBuilder: SqlQueryBuilder) {
        fun set(setClauses: Map<String, String>): SqlQueryBuilder {
            val setClause = setClauses.entries.joinToString(", ") { (columnName, value) -> "$columnName = '$value'" }
            sqlQueryBuilder.setClause = setClause
            return sqlQueryBuilder
        }
    }

    @JvmRecord
    data class SqlQueryBuilderInsertInto(private val sqlQueryBuilder: SqlQueryBuilder) {
        fun values(vararg valuesClauses: String): SqlQueryBuilder {
            val valuesClause = "(" + valuesClauses.joinToString(", ") { "'$it'" } + ")"
            sqlQueryBuilder.valuesClause = valuesClause
            return sqlQueryBuilder
        }
    }

    fun select(selectClause: String): SqlQueryBuilder {
        this.currentQueryType = QueryType.SELECT
        this.selectClause = selectClause
        return this
    }

    fun selectDistinct(selectClause: String): SqlQueryBuilder {
        currentQueryType = QueryType.SELECT_DISTINCT
        return select(selectClause)
    }

    fun delete(): SqlQueryBuilder {
        this.currentQueryType = QueryType.DELETE
        this.isDeleteQuery = true
        return this
    }

    fun update(tableName: String): SqlQueryBuilderUpdate {
        this.updateClause = tableName
        this.currentQueryType = QueryType.UPDATE
        return SqlQueryBuilderUpdate(this)
    }

    fun insertInto(tableName: String, vararg columnNames: String): SqlQueryBuilderInsertInto {
        this.currentQueryType = QueryType.INSERT_INTO
        this.insertIntoClause = "$tableName (${columnNames.joinToString(", ")})"
        return SqlQueryBuilderInsertInto(this)
    }

    fun from(fromClause: String): SqlQueryBuilder {
        if (currentQueryType != QueryType.SELECT && currentQueryType != QueryType.DELETE) {
            throw IllegalStateException("Bad SQL grammar. \"FROM\" is only possible if the request is of the type \"SELECT\".")
        }
        this.fromClause = fromClause
        return this
    }

    private fun join(joinType: String, tableName: String): SqlQueryBuilderJoin {
        return SqlQueryBuilderJoin(this, joinType, tableName)
    }

    fun leftJoin(tableName: String): SqlQueryBuilderJoin {
        return join("LEFT", tableName)
    }

    fun rightJoin(tableName: String): SqlQueryBuilderJoin {
        return join("RIGHT", tableName)
    }

    fun innerJoin(tableName: String): SqlQueryBuilderJoin {
        return join("INNER", tableName)
    }


    fun where(whereClause: String): SqlQueryBuilder {
        whereClauses.add(whereClause)
        return this
    }

    fun build(): String {
        val sql = StringBuilder()

        when (currentQueryType) {
            QueryType.SELECT -> {
                sql.append("SELECT ").append(selectClause).append(" ")
                sql.append("FROM ").append(fromClause).append(" ")
            }

            QueryType.SELECT_DISTINCT -> {
                sql.append("SELECT DISTINCT ").append(selectClause).append(" ")
                sql.append("FROM ").append(fromClause).append(" ")
            }

            QueryType.INSERT_INTO -> {
                sql.append("INSERT INTO ").append(insertIntoClause).append(" VALUES ").append(valuesClause).append(" ")
            }

            QueryType.UPDATE -> {
                sql.append("UPDATE ").append(updateClause).append(" SET ").append(setClause).append(" ")
            }

            QueryType.DELETE -> {
                sql.append("DELETE ")
                sql.append("FROM ").append(fromClause).append(" ")
            }

        }

        for (join in joinClauses) {
            sql.append(join).append(" ")
        }

        if (whereClauses.isNotEmpty()) {
            sql.append("WHERE ")
            for (i in whereClauses.indices) {
                if (i > 0) {
                    sql.append("AND ")
                }
                sql.append(whereClauses[i]).append(" ")
            }
        }

        return sql.toString()
    }
}
