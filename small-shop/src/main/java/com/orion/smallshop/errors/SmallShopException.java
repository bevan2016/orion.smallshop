package com.orion.smallshop.errors;

public class SmallShopException extends Exception {
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;

	public SmallShopException(ErrorCode errorCode) {
		this(null, errorCode);
	}

	public SmallShopException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		if (errorCode == null)
			// no need to localize since the message is only for internal use.
			throw new IllegalArgumentException("ErrorCode is null.");
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
