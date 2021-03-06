package com.github.javiersantos.appupdater;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.github.javiersantos.appupdater.objects.GitHub;

class UtilsDisplay {

    static void showUpdateAvailableDialog(final Context context, String title, String content, final UpdateFrom updateFrom, final GitHub gitHub) {
        final LibraryPreferences libraryPreferences = new LibraryPreferences(context);

        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(context.getResources().getString(R.string.appupdater_btn_update))
                .negativeText(context.getResources().getString(android.R.string.cancel))
                .neutralText(context.getResources().getString(R.string.appupdater_btn_disable))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        UtilsLibrary.goToUpdate(context, updateFrom, gitHub);
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        libraryPreferences.setAppUpdaterShow(false);
                    }
                }).show();
    }

    static void showUpdateNotAvailableDialog(final Context context, String title, String content) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(context.getResources().getString(android.R.string.ok))
                .show();
    }

    static void showUpdateAvailableSnackbar(final Context context, String content, Boolean indefinite, final UpdateFrom updateFrom, final GitHub gitHub) {
        Activity activity = (Activity) context;
        int snackbarTime;

        if (indefinite) {
            snackbarTime = Snackbar.LENGTH_INDEFINITE;
        } else {
            snackbarTime = Snackbar.LENGTH_LONG;
        }

        Snackbar snackbar = Snackbar.make(activity.getWindow().getDecorView().getRootView(), content, snackbarTime);
        snackbar.setAction(context.getResources().getString(R.string.appupdater_btn_update), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilsLibrary.goToUpdate(context, updateFrom, gitHub);
            }
        }).show();
    }

    static void showUpdateNotAvailableSnackbar(final Context context, String content, Boolean indefinite) {
        Activity activity = (Activity) context;
        int snackbarTime;

        if (indefinite) {
            snackbarTime = Snackbar.LENGTH_INDEFINITE;
        } else {
            snackbarTime = Snackbar.LENGTH_LONG;
        }

        Snackbar.make(activity.getWindow().getDecorView().getRootView(), content, snackbarTime).show();
    }

    static void showUpdateAvailableNotification(Context context, String title, String content, UpdateFrom updateFrom, GitHub gitHub) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage(UtilsLibrary.getAppPackageName(context)), PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent pendingIntentUpdate = PendingIntent.getActivity(context, 0, UtilsLibrary.intentToUpdate(context, updateFrom, gitHub), PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Style style = new NotificationCompat.BigTextStyle().bigText(content);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(style)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_system_update_white_24dp, context.getResources().getString(R.string.appupdater_btn_update), pendingIntentUpdate);

        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            builder.setSmallIcon(applicationInfo.icon);
        } catch (PackageManager.NameNotFoundException ignore) {}

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    static void showUpdateNotAvailableNotification(Context context, String title, String content) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage(UtilsLibrary.getAppPackageName(context)), PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Style style = new NotificationCompat.BigTextStyle().bigText(content);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(style)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setOnlyAlertOnce(true)
                .setAutoCancel(true);
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            builder.setSmallIcon(applicationInfo.icon);
        } catch (PackageManager.NameNotFoundException ignore) {}

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

}
