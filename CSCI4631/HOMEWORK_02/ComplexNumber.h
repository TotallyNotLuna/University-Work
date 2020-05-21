#include <iostream>

class ComplexNumber {

	public:

		ComplexNumber(); // default constructor
		ComplexNumber(float _a, float _b);// copy constructor
		ComplexNumber(const ComplexNumber& _rhs);

		ComplexNumber operator+ (const ComplexNumber& otherNumber);
		ComplexNumber operator- (const ComplexNumber& otherNumber);
		ComplexNumber operator* (const ComplexNumber& otherNumber);
		ComplexNumber operator/ (const ComplexNumber& otherNumber);

		bool operator== (const ComplexNumber& otherNumber);

		float GetA();
		float GetB();

		//friend declaration let this opperator access our float, makes getter methods pointless.
		friend std::ostream& operator<< (std::ostream& os, const ComplexNumber& s);


	 // The instance variables a and b representing
	 // the real parts of the complex number
	private:

		float a;
		float b;
};