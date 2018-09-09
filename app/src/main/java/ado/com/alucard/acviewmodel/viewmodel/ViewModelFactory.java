package ado.com.alucard.acviewmodel.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

  private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels;

  @Inject
  public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels) {
    this.viewModels = viewModels;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    try {
      //noinspection unchecked
      return (T) viewModels.get(modelClass).get();
    } catch (Exception e) {
      throw new RuntimeException("Error creating view model for class: " + modelClass.getSimpleName(), e);
    }
  }
}
