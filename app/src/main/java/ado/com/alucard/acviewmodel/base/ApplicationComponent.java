package ado.com.alucard.acviewmodel.base;

import ado.com.alucard.acviewmodel.networking.NetworkModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
    NetworkModule.class
})
public abstract class ApplicationComponent {
}
