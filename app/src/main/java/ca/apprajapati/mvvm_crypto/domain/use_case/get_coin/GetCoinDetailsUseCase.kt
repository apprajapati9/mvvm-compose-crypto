package ca.apprajapati.mvvm_crypto.domain.use_case.get_coin

import ca.apprajapati.mvvm_crypto.common.Resource
import ca.apprajapati.mvvm_crypto.data.remote.GetRetrofit
import ca.apprajapati.mvvm_crypto.data.remote.dto.toCoinDetail
import ca.apprajapati.mvvm_crypto.data.repository.CoinRepositoryImpl
import ca.apprajapati.mvvm_crypto.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinDetailsUseCase{

    //Obtaining repository without dependency injection, trying to get an instance of repository.
    //This is to learn and see how to do this without DI and then with DI, so you can understand the value of DI.
    private val repository = CoinRepositoryImpl(GetRetrofit.api)

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow{

        emit(Resource.Loading())

        try{
            val response = repository.getCoinDetails(coinId).toCoinDetail()
            emit(Resource.Success(response))

        }catch (e : HttpException){
            e.printStackTrace()
            emit(Resource.Error(message = "Unexpected error. Try again."))

        }catch (e: IOException){
            e.printStackTrace()
            emit(Resource.Error(message = "Unexpected error. Try again."))
        }
    }
}