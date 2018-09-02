package ado.com.alucard.acviewmodel.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ado.com.alucard.acviewmodel.model.Repo;

public class SelectedRepoViewModel extends ViewModel {

  private final MutableLiveData<Repo> selectedRepo = new MutableLiveData<>();

  public MutableLiveData<Repo> getSelectedRepo() {
    return selectedRepo;
  }

  void setSelectedRepo(Repo repo) {
    selectedRepo.setValue(repo);
  }
}
