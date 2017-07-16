package com.wyat.wyat.events.fragments;

import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.layouts.BasicWizardLayout;

/**
 * Created by zack on 27/01/17.
 */

public class PostEventWizard extends BasicWizardLayout {

    public PostEventWizard() {
        super();
    }

    @Override
    public WizardFlow onSetup() {

        return new WizardFlow.Builder().
                addStep(PostEventFragment1.class).
                addStep(PostEventFragment2.class).create();
    }

    @Override
    public void onWizardComplete() {
        super.onWizardComplete();
        getActivity().finish();
    }
}
