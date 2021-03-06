package ado.com.alucard.acviewmodel.viewmodel;

import ado.com.alucard.acviewmodel.home.ListViewModel;
import ado.com.alucard.acviewmodel.home.SelectedRepoViewModel;
import android.arch.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(ListViewModel.class)
  abstract ViewModel bindListViewModel(ListViewModel viewModel);

  @Binds
  @IntoMap
  @ViewModelKey(SelectedRepoViewModel.class)
  abstract ViewModel bindSelectedRepoViewModel(SelectedRepoViewModel viewModel);

}
