package ca.apprajapati.mvvm_crypto.domain.use_case.get_coins

import ca.apprajapati.mvvm_crypto.common.Resource
import ca.apprajapati.mvvm_crypto.data.remote.GetRetrofit
import ca.apprajapati.mvvm_crypto.data.remote.dto.toCoin
import ca.apprajapati.mvvm_crypto.data.repository.CoinRepositoryImpl
import ca.apprajapati.mvvm_crypto.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinsUseCase() {

    private val repository = CoinRepositoryImpl(GetRetrofit.api)

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{

        emit(Resource.Loading())

        try{
            val response = repository.getCoins().map { it.toCoin()}
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