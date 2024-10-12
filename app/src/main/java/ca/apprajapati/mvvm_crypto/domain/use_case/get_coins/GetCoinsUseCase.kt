package ca.apprajapati.mvvm_crypto.domain.use_case.get_coins

import ca.apprajapati.mvvm_crypto.common.Resource
import ca.apprajapati.mvvm_crypto.data.remote.dto.toCoin
import ca.apprajapati.mvvm_crypto.domain.model.Coin
import ca.apprajapati.mvvm_crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

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