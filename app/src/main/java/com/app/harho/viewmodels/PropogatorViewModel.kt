package com.app.harho.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.harho.adapter.propogatorDate.PropogatorDateResponse
import com.app.harho.model.propogator.productDetail.PropogatorProductDetailResponse
import com.app.harho.model.propogator.propogatorNumber.PropogatorNumberResponse
import com.app.harho.model.propogator.propogatorProduct.PropogatorProductResponse
import com.app.harho.model.propogator.propogatorRocket.PropogatorRocketResponse
import com.app.harho.model.propogator.propogatorTittle.PropogatorTittleResponse
import com.app.harho.model.question.QuestionResponse
import com.app.harho.network.Repository
import com.app.harho.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PropogatorViewModel(val application: Application, val repository: Repository) : ViewModel() {
    val resultPropogatorTittle = MutableLiveData<Resource<PropogatorTittleResponse>>()
    val resultPropogatorProductDetail = MutableLiveData<Resource<PropogatorProductDetailResponse>>()
    val resultPropogatorNumber = MutableLiveData<Resource<PropogatorNumberResponse>>()
    val resultPropogatorDate = MutableLiveData<Resource<PropogatorDateResponse>>()
    val resultPropogatorRocket = MutableLiveData<Resource<PropogatorRocketResponse>>()
    val resultPropogatorProduct = MutableLiveData<Resource<PropogatorProductResponse>>()
    val resultQuestion = MutableLiveData<Resource<QuestionResponse>>()

    fun onPropogatorRocket(id: String) {
        resultPropogatorRocket.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPropogatorRocket(id)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultPropogatorRocket.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultPropogatorRocket.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultPropogatorRocket.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultPropogatorRocket.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onPropogatorTittle() {
        resultPropogatorTittle.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPropogatorTittle()
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultPropogatorTittle.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultPropogatorTittle.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultPropogatorTittle.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultPropogatorTittle.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onQuestion() {
        resultPropogatorTittle.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getQuestion()
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultQuestion.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultQuestion.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultQuestion.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultQuestion.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onPropogatorProductDetail() {
        resultPropogatorProductDetail.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPropogatorProductDetail()
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultPropogatorProductDetail.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultPropogatorProductDetail.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultPropogatorProductDetail.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultPropogatorProductDetail.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onPropogatorNumber(id: String) {
        resultPropogatorNumber.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPropogatorNumber(id)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultPropogatorNumber.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultPropogatorNumber.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultPropogatorNumber.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultPropogatorNumber.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onPropogatorProduct(id: String) {
        resultPropogatorProduct.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPropogatorProduct(id)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultPropogatorProduct.value = Resource.success(
                                response.body(),
                                response.body()!!.message.toString()
                            )
                        } else {
                            resultPropogatorProduct.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultPropogatorProduct.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultPropogatorProduct.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }

    fun onPropogatorDate(id: String, month: String) {
        resultPropogatorDate.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPropogatorDate(id, month)
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        if (response.body()?.code == 200) {
                            resultPropogatorDate.value = Resource.success(
                                response.body(),
                                response.body()!!.message
                            )
                        } else {
                            resultPropogatorDate.value =
                                Resource.error(null, response.body()?.message.toString())
                        }
                    } else {
                        resultPropogatorDate.value =
                            Resource.error(null, response.body()?.message.toString())

                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultPropogatorDate.value = Resource.error(null, t.message.toString())
                }
            }
        }
    }
}