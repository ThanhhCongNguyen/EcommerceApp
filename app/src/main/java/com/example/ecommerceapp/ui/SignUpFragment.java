package com.example.ecommerceapp.ui;

import static com.example.ecommerceapp.utils.Utilities.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentSignUpBinding;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.utils.Utilities;
import com.example.ecommerceapp.utils.Validate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class SignUpFragment extends Fragment implements View.OnClickListener {
    private FragmentSignUpBinding binding;
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
        database.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onRegisterSuccess(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error writing document", e);
                    }
                });


//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            binding.progressBar.setVisibility(View.GONE);
//                            Log.d("thanh", "createUserWithEmail:success");
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            callback.onRegisterSuccess(user);
//                        } else {
//                            //Fail
//                            binding.progressBar.setVisibility(View.GONE);
//                            Log.d("thanh", "createUserWithEmail:failure", task.getException());
//                            Utilities.toast(getString(R.string.authen_fail), getActivity());
//                        }
//                    }
//                });
    }

}