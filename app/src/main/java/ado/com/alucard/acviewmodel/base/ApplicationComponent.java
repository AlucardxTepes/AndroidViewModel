package ado.com.alucard.acviewmodel.base;

import ado.com.alucard.acviewmodel.networking.NetworkModule;
import ado.com.alucard.acviewmodel.viewmodel.ViewModelModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
    NetworkModule.class,
    ViewModelModule.class
})
public interface ApplicationComponent {
}
