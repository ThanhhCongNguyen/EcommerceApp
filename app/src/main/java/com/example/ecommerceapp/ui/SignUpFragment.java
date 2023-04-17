package com.example.ecommerceapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentSignUpBinding;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.utils.Validate;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class SignUpFragment extends Fragment implements View.OnClickListener {
    private FragmentSignUpBinding binding;
    private HomeViewModel homeViewModel;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore database;
    private Callback callback;

    public interface Callback {
        void onRegisterSuccess(User user);

        void navigateLoginFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            this.callback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement OnItemClickedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButton:
                registerNewUser();
            case R.id.loginButton:
                navigateLogin();
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void registerNewUser() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String userName = binding.nameEdittext.getText().toString().trim();
        String email = binding.emailEdittext.getText().toString().trim();
        String password = binding.passwordEdittext.getText().toString().trim();
        String confirmPassword = binding.confirmPasswordEdittext.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            binding.nameTextField.setError(getString(R.string.enter_name_require));
            return;
        } else {
            binding.nameTextField.setErrorEnabled(false);
        }

        if (TextUtils.isEmpty(email)) {
            binding.emailTextField.setError(getString(R.string.enter_phone_number_require));
            return;
        } else {
            binding.emailTextField.setErrorEnabled(false);
        }

        if (!Validate.checkIsValidEmailAddress(email)) {
            binding.emailTextField.setError(getString(R.string.number_not_correct_format));
            return;
        } else {
            binding.emailTextField.setErrorEnabled(false);
        }

        if (!Validate.checkPasswordLength(password)) {
            binding.passwordTextField.setError(getString(R.string.check_password_length));
            return;
        } else {
            binding.passwordTextField.setErrorEnabled(false);
        }

        if (!Validate.checkPasswordAndConfirm(password, confirmPassword)) {
            binding.confirmPasswordTextField.setError(getString(R.string.confirm_password_not_correct));
            return;
        } else {
            binding.confirmPasswordTextField.setErrorEnabled(false);
        }

        String userId = UUID.randomUUID().toString();
        User user = new User(userId, userName, email, password);
        homeViewModel.createNewUser(user);
    }

    private void navigateLogin() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, loginFragment);
        fragmentTransaction.commit();
    }

}