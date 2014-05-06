package com.example.nutrihawk;

public class VitaminSet {
	private int A;
	private int B1;
	private int B2;
	private int B3;
	private int B5;
	private int B6;
	private int B7;
	private int B9;
	private int B12;
	private int C;
	private int D;
	private int E;
	private int K;
	
	public VitaminSet() {
		A = 0;
		B1 = 0;
		B2 = 0;
		B3 = 0;
		B5 = 0;
		B6 = 0;
		B7 = 0;
		B9 = 0;
		B12 = 0;
		C = 0;
		D = 0;
		E = 0;
		K = 0;
	}
	
	public VitaminSet(int a, int b1, int b2, int b3, int b5, int b6, int b7, int b9, int b12, int c, int d, int e, int k) {
		A = a;
		B1 = b1;
		B2 = b2;
		B3 = b3;
		B5 = b5;
		B6 = b6;
		B7 = b7;
		B9 = b9;
		B12 = b12;
		C = c;
		D = d;
		E = e;
		K = k;
	}
}
