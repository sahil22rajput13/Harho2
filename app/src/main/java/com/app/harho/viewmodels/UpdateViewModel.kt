package com.app.harho.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.harho.model.auth.update.boxarrived.BoxArrivedPramModel
import com.app.harho.model.auth.update.isupdate.IsUpdatePramModel
import com.app.harho.model.auth.update.readytogrow.ReadyToGrowPramModel
import com.app.harho.model.auth.update.recieved.RecievedPramModel
import com.app.harho.model.auth.update.updateResponse
import com.app.harho.model.leaderBoard.LeaderBoardResponse
import com.app.harho.network.Repository
import com.app.harho.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateViewModel(val application: Application, val repository: Repository) : ViewModel() {

    val resultBoxArrived = MutableLiveData<Resource<updateResponse>>()
    val resultReadyToGrow = MutableLiveData<Resource<updateResponse>>()
    val resultRecieved = MutableLiveData<Resource<updateResponse>>()
    val resultIsUpdate = MutableLiveData<Resource<updateResponse>>()
    val resultLeaderBoard = MutableLiveData<Resource<LeaderBoardResponse>>()


    fun onBoxArrived(body: BoxArrivedPramModel) {
        resultBoxArrived.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.onBoxArrived(body)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultBoxArrived.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultBoxArrived.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultBoxArrived.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultBoxArrived.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onLeaderBoard(offset: String, limit: String) {
        resultBoxArrived.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getLeaderBoard(offset, limit)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultLeaderBoard.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultLeaderBoard.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultLeaderBoard.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultLeaderBoard.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onReadyToGrow(body: ReadyToGrowPramModel) {
        resultReadyToGrow.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.onReadyToGrow(body)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultReadyToGrow.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultReadyToGrow.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultReadyToGrow.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultReadyToGrow.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onRecieved(body: RecievedPramModel) {
        resultRecieved.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.onRecieved(body)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultRecieved.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultRecieved.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultRecieved.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultRecieved.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onIsUpdate(body: IsUpdatePramModel) {
        resultIsUpdate.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.onIsUpdate(body)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultIsUpdate.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultIsUpdate.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultIsUpdate.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultIsUpdate.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

}