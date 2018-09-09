package ado.com.alucard.acviewmodel.networking;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.inject.Singleton;

@Module
public abstract class NetworkModule {

  private static final String BASE_URL = "https://api.github.com/";

  @Provides
  @Singleton
  static Retrofit provideRetrofit() {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
  }

  @Provides
  @Singleton
  static RepoService provideRepoService(Retrofit retrofit) {
    return retrofit.create(RepoService.class);
  }

}
