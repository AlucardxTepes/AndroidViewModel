package ado.com.alucard.acviewmodel.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ado.com.alucard.acviewmodel.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ListFragment extends Fragment {

  @BindView(R.id.recycler_view) RecyclerView listView;
  @BindView(R.id.tv_error) TextView errorTextView;
  @BindView(R.id.loading_view) View loadingView;

  private Unbinder unbinder;
  private ListViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.screen_list, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

    listView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    listView.setAdapter(new RepoListAdapter(viewModel, this));
    listView.setLayoutManager(new LinearLayoutManager(getContext()));
    observeViewModel();
  }

  @SuppressWarnings("ConstantConditions")
  private void observeViewModel() {
    viewModel.getRepos().observe(this, repos -> {
      if (repos != null) {
        listView.setVisibility(VISIBLE);
      }
    });
    viewModel.getRepoLoadError().observe(this, isError -> {
      if (isError) {
        listView.setVisibility(GONE);
        errorTextView.setVisibility(VISIBLE);
        errorTextView.setText(R.string.api_error_repos);
      } else {
        errorTextView.setVisibility(GONE);
        errorTextView.setText(null);
      }
    });
    viewModel.getLoading().observe(this, isLoading -> {
      loadingView.setVisibility(isLoading ? VISIBLE: GONE);
      if (isLoading) {
        errorTextView.setVisibility(GONE);
        listView.setVisibility(GONE);
      }
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }
  }
}
