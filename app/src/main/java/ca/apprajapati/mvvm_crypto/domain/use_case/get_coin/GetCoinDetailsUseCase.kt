package ca.apprajapati.mvvm_crypto.domain.use_case.get_coin

import ca.apprajapati.mvvm_crypto.common.Resource
import ca.apprajapati.mvvm_crypto.data.remote.dto.toCoinDetail
import ca.apprajapati.mvvm_crypto.domain.model.CoinDetail
import ca.apprajapati.mvvm_crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//removed manual injection after Hilt DI
class GetCoinDetailsUseCase(private val repository: CoinRepository){

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