package com.example.mosbysample.mosbyMVI

interface ViewStateActivity {

    //StateLoad
    class LoadingState : ViewStateActivity {
        override fun toString(): String {
            return "LoadingState{}"
        }
    }

    //ErrorState
    class ErrorState(private val error: Throwable) : ViewStateActivity {
        fun getErrorMessage(): String {
            return error.message.toString()
        }

        override fun toString(): String {
            return "ErrorState ${error.message}"
        }
    }

    //Load success
    class DataState(val mviState: MviState) : ViewStateActivity {

        override fun toString(): String {
            return "Load success DataSate ${mviState.data.size}"
        }
    }

}