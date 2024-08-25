package com.jmgc.prueba.data.remote.dto.poke

data class MoveDto(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)