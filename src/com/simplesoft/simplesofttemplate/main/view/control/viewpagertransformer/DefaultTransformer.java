package com.simplesoft.simplesofttemplate.main.view.control.viewpagertransformer;

import android.view.View;

public class DefaultTransformer extends ABaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
	}

	@Override
	public boolean isPagingEnabled() {
		return true;
	}

}
