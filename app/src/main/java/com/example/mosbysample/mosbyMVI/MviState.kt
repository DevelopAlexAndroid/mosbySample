package com.example.mosbysample.mosbyMVI

import com.example.mosbysample.mosbyMVI.model.PersonModel

class MviState constructor(
    val error: Boolean,
    val loading: Boolean,
    val data : List<PersonModel>
)