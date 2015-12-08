package com.feynmanliang.gradopt

import breeze.linalg._
import breeze.numerics._

import org.scalatest._

import com.feynmanliang.gradopt.GradientAlgorithm._
import com.feynmanliang.gradopt.LineSearchConfig._


class ConjugateGradientSuite extends FunSpec {
  val opt = new Optimizer()
  describe("Conjugate gradient") {
    it("should run on Rosenbrock function") {
      val f: Vector[Double] => Double = v => {
        pow(1D - v(0), 2) + 100D * pow(v(1) - pow(v(0), 2),2)
      }
      val df: Vector[Double] => Vector[Double] = v => {
        DenseVector(
          -2D*(1 - v(0)) - 400D * v(0) * (-pow(v(0), 2) + v(1)),
          200D * (-pow(v(0), 2) + v(1))
        )
      }

      opt.minimize(f, df, DenseVector(2D,1D), ConjugateGradient, CubicInterpolation, true)
    }
  }
}

// vim: set ts=2 sw=2 et sts=2:
