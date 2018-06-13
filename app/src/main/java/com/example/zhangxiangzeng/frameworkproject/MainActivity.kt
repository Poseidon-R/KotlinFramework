package com.example.zhangxiangzeng.frameworkproject

import android.animation.*
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import com.example.zhangxiangzeng.frameworkproject.component.JellyInterpolator
import com.example.zhangxiangzeng.frameworkproject.rest.ApiService
import com.example.zhangxiangzeng.frameworkproject.rest.async
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : Activity(), View.OnClickListener {

    @Inject
    lateinit var retrofit: Retrofit

    private lateinit var mBtnLogin: TextView
    private lateinit var progress: View
    private lateinit var mInputLayout: View
    private lateinit var mName: LinearLayout
    private lateinit var mPsw: LinearLayout

    private var mWidth: Float = 0F
    private var mHeight: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        (application as App).getNetComponent().inject(this)

        retrofit.create(ApiService::class.java)
                .doLogin("15211168600", "skkey123", "app")
                .async()
                .subscribeBy(
                        onNext = {

                        },
                        onError = {

                        }
                )
        initView()
//        DaggerNetComponent.builder()
//                .netModule(NetModule())
//                .build()
//                .inject(this)
//        val result = apiService.fetchPlacesByKey("d", 1, 1) {
//            ToastUtils.showShort("请求失败")
//        }
//        result.observe(this, Observer {
//            if (it == null) return@Observer
//            ToastUtils.showShort("搜索到结果:${it.size}条")
//        })
    }

    private fun initView() {
        mBtnLogin = findViewById(R.id.main_btn_login)
        progress = findViewById(R.id.layout_progress)
        mInputLayout = findViewById(R.id.input_layout)
        mName = findViewById(R.id.input_layout_name)
        mPsw = findViewById(R.id.input_layout_psw)
        mBtnLogin.setOnClickListener(this)
    }


    override fun onClick(v: View) {

        mWidth = mBtnLogin.measuredWidth.toFloat()
        mHeight = mBtnLogin.measuredHeight.toFloat()

        mName.visibility = View.INVISIBLE
        mPsw.visibility = View.INVISIBLE

        inputAnimator(mInputLayout, mWidth, mHeight)

    }

    private fun inputAnimator(view: View, w: Float, h: Float) {


        val animator = ValueAnimator.ofFloat(0F, w)
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.leftMargin = value.toInt()
            params.rightMargin = value.toInt()
            view.layoutParams = params
        }
        val set = AnimatorSet()
        val animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 1f, 0.5f)
        set.duration = 1000
        set.interpolator = AccelerateDecelerateInterpolator()
        set.playTogether(animator, animator2)
        set.start()
        set.addListener(object : Animator.AnimatorListener {


            override fun onAnimationStart(animation: Animator) {

            }


            override fun onAnimationRepeat(animation: Animator) {
                // TODO Auto-generated method stub

            }


            override fun onAnimationEnd(animation: Animator) {

                progress.visibility = View.VISIBLE
                progressAnimator(progress)
                mInputLayout.visibility = View.INVISIBLE

                Handler().postDelayed({ recovery() }, 2000)
            }


            override fun onAnimationCancel(animation: Animator) {
                // TODO Auto-generated method stub

            }
        })

    }

    private fun progressAnimator(view: View) {
        val animator = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f)
        val animator2 = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f)
        val animator3 = ObjectAnimator.ofPropertyValuesHolder(view, animator, animator2)
        animator3.duration = 1000
        animator3.interpolator = JellyInterpolator()
        animator3.start()

    }

    private fun recovery() {
        progress.visibility = View.GONE
        mInputLayout.visibility = View.VISIBLE
        mName.visibility = View.VISIBLE
        mPsw.visibility = View.VISIBLE

        val params = mInputLayout.layoutParams as ViewGroup.MarginLayoutParams
        params.leftMargin = 0
        params.rightMargin = 0
        mInputLayout.layoutParams = params


        val animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f, 1f)
        animator2.duration = 500
        animator2.interpolator = AccelerateDecelerateInterpolator()
        animator2.start()
    }
}
