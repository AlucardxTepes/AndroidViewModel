package ado.com.alucard.acviewmodel.home;

import ado.com.alucard.acviewmodel.model.Repo;
import ado.com.alucard.acviewmodel.networking.RepoService;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;

public class SelectedRepoViewModel extends ViewModel {

  private final MutableLiveData<Repo> selectedRepo = new MutableLiveData<>();
  private Call<Repo> repoCall;
  private RepoService repoService;

  @Inject
  public SelectedRepoViewModel(RepoService repoService) {
    this.repoService = repoService;
  }

  public MutableLiveData<Repo> getSelectedRepo() {
    return selectedRepo;
  }

  void setSelectedRepo(Repo repo) {
    selectedRepo.setValue(repo);
  }

  public void saveToBundle(Bundle outState) {
    if (selectedRepo.getValue() != null) {
      outState.putStringArray("repo_details", new String[]{
          selectedRepo.getValue().owner.login,
          selectedRepo.getValue().name
      });
    }
  }

  public void restoreFromBundle(Bundle savedInstanceState) {
    if (selectedRepo.getValue() == null) {
      // Restoring only if we don't have a selected Repo set already
      if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
        loadRepo(savedInstanceState.getStringArray("repo_details"));
      }
    }
  }

  private void loadRepo(String[] repoDetails) {
    repoCall = repoService.getRepo(repoDetails[0], repoDetails[1]);
    repoCall.enqueue(new Callback<Repo>() {
      @Override
      public void onResponse(Call<Repo> call, Response<Repo> response) {
        selectedRepo.setValue(response.body());
        repoCall = null;
      }

      @Override
      public void onFailure(Call<Repo> call, Throwable t) {
        Log.e(getClass().getSimpleName(), "Error Loading Repo", t);
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
