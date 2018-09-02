package ado.com.alucard.acviewmodel.networking;

import java.util.List;

import ado.com.alucard.acviewmodel.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {

  @GET("orgs/Google/repos")
  Call<List<Repo>> getRepositories();

  @GET("repos/{owner}/{name}")
  Call<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);

}
