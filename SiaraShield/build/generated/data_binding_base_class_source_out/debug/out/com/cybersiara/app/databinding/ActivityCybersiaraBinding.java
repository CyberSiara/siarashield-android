// Generated by view binder compiler. Do not edit!
package com.cybersiara.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cybersiara.app.R;
import com.example.swipebutton_library.SwipeButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCybersiaraBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CardView cardCaptcha;

  @NonNull
  public final FrameLayout frameActive;

  @NonNull
  public final FrameLayout frameWithoutActive;

  @NonNull
  public final ImageView imgGif;

  @NonNull
  public final LinearLayout lSubmit;

  @NonNull
  public final LinearLayout mainLayout;

  @NonNull
  public final SwipeButton swipeBtn1;

  @NonNull
  public final TextView txtNoAuth;

  @NonNull
  public final TextView txtPrivacy;

  @NonNull
  public final TextView txtTerms;

  private ActivityCybersiaraBinding(@NonNull LinearLayout rootView, @NonNull CardView cardCaptcha,
      @NonNull FrameLayout frameActive, @NonNull FrameLayout frameWithoutActive,
      @NonNull ImageView imgGif, @NonNull LinearLayout lSubmit, @NonNull LinearLayout mainLayout,
      @NonNull SwipeButton swipeBtn1, @NonNull TextView txtNoAuth, @NonNull TextView txtPrivacy,
      @NonNull TextView txtTerms) {
    this.rootView = rootView;
    this.cardCaptcha = cardCaptcha;
    this.frameActive = frameActive;
    this.frameWithoutActive = frameWithoutActive;
    this.imgGif = imgGif;
    this.lSubmit = lSubmit;
    this.mainLayout = mainLayout;
    this.swipeBtn1 = swipeBtn1;
    this.txtNoAuth = txtNoAuth;
    this.txtPrivacy = txtPrivacy;
    this.txtTerms = txtTerms;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCybersiaraBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCybersiaraBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_cybersiara, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCybersiaraBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.card_captcha;
      CardView cardCaptcha = ViewBindings.findChildViewById(rootView, id);
      if (cardCaptcha == null) {
        break missingId;
      }

      id = R.id.frame_active;
      FrameLayout frameActive = ViewBindings.findChildViewById(rootView, id);
      if (frameActive == null) {
        break missingId;
      }

      id = R.id.frame_without_active;
      FrameLayout frameWithoutActive = ViewBindings.findChildViewById(rootView, id);
      if (frameWithoutActive == null) {
        break missingId;
      }

      id = R.id.img_gif;
      ImageView imgGif = ViewBindings.findChildViewById(rootView, id);
      if (imgGif == null) {
        break missingId;
      }

      id = R.id.l_submit;
      LinearLayout lSubmit = ViewBindings.findChildViewById(rootView, id);
      if (lSubmit == null) {
        break missingId;
      }

      LinearLayout mainLayout = (LinearLayout) rootView;

      id = R.id.swipe_btn_1;
      SwipeButton swipeBtn1 = ViewBindings.findChildViewById(rootView, id);
      if (swipeBtn1 == null) {
        break missingId;
      }

      id = R.id.txt_no_auth;
      TextView txtNoAuth = ViewBindings.findChildViewById(rootView, id);
      if (txtNoAuth == null) {
        break missingId;
      }

      id = R.id.txt_privacy;
      TextView txtPrivacy = ViewBindings.findChildViewById(rootView, id);
      if (txtPrivacy == null) {
        break missingId;
      }

      id = R.id.txt_terms;
      TextView txtTerms = ViewBindings.findChildViewById(rootView, id);
      if (txtTerms == null) {
        break missingId;
      }

      return new ActivityCybersiaraBinding((LinearLayout) rootView, cardCaptcha, frameActive,
          frameWithoutActive, imgGif, lSubmit, mainLayout, swipeBtn1, txtNoAuth, txtPrivacy,
          txtTerms);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
