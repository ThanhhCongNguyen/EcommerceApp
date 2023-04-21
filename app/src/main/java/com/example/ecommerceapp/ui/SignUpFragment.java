package com.example.ecommerceapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentSignUpBinding;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.utils.PaymentMethod;
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
    private boolean isValidName;
    private boolean isValidEmail;
    private boolean isValidPassword;
    private boolean isValidConfirmPassword;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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
        binding.loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButton:
                registerNewUser();
                return;
            case R.id.loginButton:
                navigateLogin();
                return;
        }
    }

    private void registerNewUser() {
        String userName = binding.nameEdittext.getText().toString().trim();
        String email = binding.emailEdittext.getText().toString().trim();
        String password = binding.passwordEdittext.getText().toString().trim();
        String confirmPassword = binding.confirmPasswordEdittext.getText().toString().trim();

        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword)) {
            binding.nameTextField.setError(getString(R.string.enter_name_require));
            binding.emailTextField.setError(getString(R.string.enter_phone_number_require));
            binding.passwordTextField.setError("You need to enter your password");
            binding.confirmPasswordTextField.setError("You need to enter your confirm password");
            isValidPassword = false;
            isValidConfirmPassword = false;
            isValidName = false;
            isValidEmail = false;
        } else {
            if (TextUtils.isEmpty(userName)) {
                binding.nameTextField.setError(getString(R.string.enter_name_require));
                isValidName = false;
            } else {
                binding.nameTextField.setErrorEnabled(false);
                isValidName = true;
            }

            if (TextUtils.isEmpty(email)) {
                binding.emailTextField.setError(getString(R.string.enter_phone_number_require));
                isValidEmail = false;
            } else {
                binding.emailTextField.setErrorEnabled(false);
                isValidEmail = true;
            }

            if (TextUtils.isEmpty(password)) {
                binding.passwordTextField.setError(getString(R.string.enter_phone_number_require));
                isValidPassword = false;
            } else {
                binding.passwordTextField.setErrorEnabled(false);
                isValidPassword = true;
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                binding.confirmPasswordTextField.setError(getString(R.string.enter_phone_number_require));
                isValidConfirmPassword = false;
            } else {
                binding.confirmPasswordTextField.setErrorEnabled(false);
                isValidConfirmPassword = true;
            }

            if (!Validate.checkIsValidEmailAddress(email)) {
                binding.emailTextField.setError(getString(R.string.number_not_correct_format));
                isValidEmail = false;
            } else {
                binding.emailTextField.setErrorEnabled(false);
                isValidEmail = true;
            }

            if (!Validate.checkPasswordLength(password)) {
                binding.passwordTextField.setError(getString(R.string.check_password_length));
                isValidPassword = false;
            } else {
                binding.passwordTextField.setErrorEnabled(false);
                isValidPassword = true;
            }

            if (!Validate.checkPasswordAndConfirm(password, confirmPassword)) {
                binding.confirmPasswordTextField.setError(getString(R.string.confirm_password_not_correct));
                isValidConfirmPassword = false;
            } else {
                binding.confirmPasswordTextField.setErrorEnabled(false);
                isValidConfirmPassword = true;
            }
        }

        String userId = UUID.randomUUID().toString();
        User user = new User(userId, userName, email, password, PaymentMethod.PAYMENT_ON_DELIVERY);

        if (isValidEmail && isValidName && isValidConfirmPassword && isValidPassword) {
            binding.progressBar.setVisibility(View.VISIBLE);
            homeViewModel.createNewUser(user);

            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void navigateLogin() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, loginFragment);
        fragmentTransaction.commit();
    }

}