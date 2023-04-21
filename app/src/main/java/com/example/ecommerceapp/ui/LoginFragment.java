package com.example.ecommerceapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentLoginBinding;
import com.example.ecommerceapp.utils.Validate;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private FragmentLoginBinding binding;
    private HomeViewModel homeViewModel;
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                login();
            case R.id.signInWithGoogle:
                return;
            case R.id.signUpButton:
                navigateSignUp();
                return;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void init() {
        binding.signInWithGoogle.setOnClickListener(this);
        binding.signUpButton.setOnClickListener(this);
        binding.loginButton.setOnClickListener(this);

        homeViewModel.getLiveDataLoginFail().observe(getViewLifecycleOwner(), s -> {
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Please try again: " + s, Toast.LENGTH_LONG).show();
        });

    }

    private void login() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email = binding.emailEdittext.getText().toString().trim();
        String password = binding.passwordEdittext.getText().toString().trim();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            binding.emailTextField.setError("You need to enter your email");
            binding.passwordTextField.setError("You need to enter your password");
            binding.progressBar.setVisibility(View.GONE);
            isValidEmail = false;
            isValidPassword = false;
        } else {
            if (TextUtils.isEmpty(email)) {
                binding.emailTextField.setError("You need to enter your email");
                binding.progressBar.setVisibility(View.GONE);
                isValidEmail = false;
            } else {
                isValidEmail = true;
                binding.emailTextField.setErrorEnabled(false);
            }

            if (TextUtils.isEmpty(password)) {
                binding.passwordTextField.setError("You need to enter your password");
                binding.progressBar.setVisibility(View.GONE);
                isValidPassword = false;
                return;
            } else {
                isValidPassword = true;
                binding.passwordTextField.setErrorEnabled(false);
            }

            if (!Validate.checkIsValidEmailAddress(email)) {
                binding.emailTextField.setError(getString(R.string.number_not_correct_format));
                isValidEmail = false;
            } else {
                binding.emailTextField.setErrorEnabled(false);
                isValidEmail = true;
            }
        }
        binding.emailEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    isValidEmail = true;
                    binding.emailTextField.setErrorEnabled(false);
//                } else {
//                    binding.emailTextField.setError("You need to enter a valid email");
//                    isValidEmail = false;
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.passwordEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.passwordTextField.setErrorEnabled(false);
                isValidPassword = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (isValidPassword && isValidEmail) {
            binding.progressBar.setVisibility(View.VISIBLE);
            homeViewModel.signInWithEmailAndPassword(email, password);

            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void navigateSignUp() {
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, signUpFragment);
        fragmentTransaction.commit();
    }


    private void displayToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

}