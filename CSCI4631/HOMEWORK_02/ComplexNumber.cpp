#include "pch.h"
#include "ComplexNumber.h"

	//Constructors---------------------------------------------
	ComplexNumber::ComplexNumber() : a(0.0), b(0.0){} // default constructor

	ComplexNumber::ComplexNumber(float _a, float _b) : a(_a), b(_b) {}

	// copy constructor
	ComplexNumber::ComplexNumber(const ComplexNumber& _rhs) : a(_rhs.a), b(_rhs.b) {}


	//Arithmatic Methods--------------------------------------------------

	/*
	@param ComplexNumber X
	@return ComplexNumber Y
	@desc Takes two given argument and adds them.
	*/
	ComplexNumber ComplexNumber::operator+ (const ComplexNumber& otherNumber) {

		ComplexNumber number;

		number.a = this->a + otherNumber.a;
		number.b = this->b + otherNumber.b;

		return number;
	}

	/*
	@param ComplexNumber X
	@return ComplexNumber Y
	@desc Takes two given argument and subtracts them.
	*/
	ComplexNumber ComplexNumber::operator- (const ComplexNumber& otherNumber) {

		ComplexNumber number;

		number.a = this->a - otherNumber.a;
		number.b = this->b - otherNumber.b;

		return number;
	}
	/*
	@param ComplexNumber X
	@return ComplexNumber Y
	@desc Takes two given argument and multiplies them.
	*/
	ComplexNumber ComplexNumber::operator* (const ComplexNumber& otherNumber) {

		ComplexNumber number;

		number.a = (this->a*otherNumber.a) - (this->b*otherNumber.b);
		number.b = (this->b*otherNumber.a) + (this->a*otherNumber.b);

		return number;
	}

	/*
	@param ComplexNumber X
	@return ComplexNumber Y
	@desc Takes two given argument and devides them.
	*/
	ComplexNumber ComplexNumber::operator/ (const ComplexNumber& otherNumber) {

		ComplexNumber number;

		number.a = ((this->a*otherNumber.a) + (this->b*otherNumber.b)) / (powf(otherNumber.a, 2.0) + powf(otherNumber.b, 2.0));
		number.b = ((this->b*otherNumber.a) - (this->a*otherNumber.b)) / (powf(otherNumber.a, 2.0) + powf(otherNumber.b, 2.0));

		return number;
	}

	bool ComplexNumber::operator== (const ComplexNumber& otherNumber) {
		
		if ((this->a - otherNumber.a) <= (0.00001) && (this->a - otherNumber.a) >= (-0.00001)) {

			if ((this->b - otherNumber.b) <= (0.00001) && (this->b - otherNumber.b) >= (-0.00001)) {

				return true;
			}
		}

		return false;
	}


	//Other Methods-----------------------------------------------------

	/*
	@return float a
	@desc returns the first float of a ComplexNumber.

		made obsolete by declairing operator<< as a friend member.
	*/
	float ComplexNumber::GetA() {

		return this->a;
	}

	/*
	@return float b
	@desc returns the second float of a ComplexNumber.
		
		made obsolete by declairing operator<< as a friend member.
	*/
	float ComplexNumber::GetB() {

		return this->b;
	}

	/*
	@desc defines how to properly output our ComplexNumber.
	*/
	std::ostream& operator<< (std::ostream& os, const ComplexNumber& s) {

		os << s.a << " + " << s.b << "i";

		return os;
	}