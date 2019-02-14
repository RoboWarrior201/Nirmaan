package com.nirmaan_bits.nirmaan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ContactFragment extends Fragment {

    Button logout;
    FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView recyclerView;

    private DatabaseReference databaseReference;
    private View homeView;


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerOptions<Contact> options= new FirebaseRecyclerOptions.Builder<Contact>()
                .setQuery(databaseReference,Contact.class)
                .build();

        FirebaseRecyclerAdapter<Contact,ContactViewHolder> adapter=
                new FirebaseRecyclerAdapter<Contact,ContactViewHolder>(options) {


                    @Override
                    protected void onBindViewHolder(@NonNull final ContactViewHolder holder, int position, @NonNull Contact model) {




                        String musersId = getRef(position).getKey();

                        databaseReference.child(musersId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {





                                    String mName = dataSnapshot.child("name").getValue().toString();
                                    String mNumb = dataSnapshot.child("num").getValue().toString();
                                    String mYear = dataSnapshot.child("position").getValue().toString();



                                    holder.name.setText(mName);
                                    holder.year.setText(mYear);
                                    holder.contact.setText(mNumb);



                                }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }



                    @NonNull
                    @Override
                    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
                        return new ContactViewHolder(view);
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView name,contact,year,pl ;



        ContactViewHolder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.member_name);
            contact= itemView.findViewById(R.id.member_numb);
            year= itemView.findViewById(R.id.member_year);
            pl= itemView.findViewById(R.id.member_pl);


        }

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        homeView = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = homeView.findViewById(R.id.contact_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        databaseReference = FirebaseDatabase.getInstance().getReference().child("contact");
        databaseReference.keepSynced(true);

        return homeView;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logout= Objects.requireNonNull(getView()).findViewById(R.id.signout);
        mAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("767599280120-rf9ranprhbrhp41oeej8430j6ti79pji.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(Objects.requireNonNull(getActivity()), gso);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                mGoogleSignInClient.signOut();

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null) {
                    Intent intent=new Intent(getActivity(),SignIn.class);
                    startActivity(intent);
                }
            }
        };


    }



}
