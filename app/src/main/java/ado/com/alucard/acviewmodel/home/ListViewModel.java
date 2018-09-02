package ado.com.alucard.acviewmodel.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import ado.com.alucard.acviewmodel.model.Repo;
import ado.com.alucard.acviewmodel.networking.RepoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

  private final MutableLiveData<List<Repo>> repos = new MutableLiveData<>();
  private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
  private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
  private Call<List<Repo>> repoCall;

  public ListViewModel() {
    fetchRepos();
  }

  public LiveData<List<Repo>> getRepos() {
    return repos;
  }

  public LiveData<Boolean> getRepoLoadError() {
    return repoLoadError;
  }

  public LiveData<Boolean> getLoading() {
    return loading;
  }

  private void fetchRepos() {
    loading.setValue(true);
    repoCall = RepoApi.getInstance().getRepositories();
    repoCall.enqueue(new Callback<List<Repo>>() {
      @Override
      public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        repoLoadError.setValue(false);
        repos.setValue(response.body());
        repoCall = null;
        loading.setValue(false);
      }

      @Override
      public void onFailure(Call<List<Repo>> call, Throwable t) {
        Log.e(getClass().getSimpleName(), "Error loading repos", t);
        repoLoadError.setValue(false);
        loading.setValue(false);
        repoCall = null;
      }
    });
  }

  @Override
  protected void onCleared() {
    if (repoCall != null) {
      repoCall.cancel();
      repoCall = null;
    }
  }
}
