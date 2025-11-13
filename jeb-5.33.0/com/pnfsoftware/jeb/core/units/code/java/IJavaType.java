package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaType {
   String JAVA_LANG_OBJECT = "Ljava/lang/Object;";
   String JAVA_LANG_STRING = "Ljava/lang/String;";
   String JAVA_LANG_CLASS = "Ljava/lang/Class;";
   String JAVA_LANG_ENUM = "Ljava/lang/Enum;";
   String JAVA_LANG_EXCEPTION = "Ljava/lang/Exception;";
   String JAVA_LANG_THROWABLE = "Ljava/lang/Throwable;";
   String JAVA_LANG_INVOKE_METHODTYPE = "Ljava/lang/invoke/MethodType;";
   String JAVA_LANG_INVOKE_METHODHANDLE = "Ljava/lang/invoke/MethodHandle;";
   String JAVA_LANG_INVOKE_CALLSITE = "Ljava/lang/invoke/CallSite;";

   IJavaTypeFactory getFactory();

   boolean isVoid();

   boolean isPrimitive();

   boolean isBoolean();

   boolean isByte();

   boolean isChar();

   boolean isShort();

   boolean isInt();

   boolean isLong();

   boolean isFloat();

   boolean isDouble();

   boolean isSingleSlotWildcard();

   boolean isDoubleSlotWildcard();

   boolean isSmallIntegerWildcard();

   boolean isGenericObjectWildcard();

   boolean isObjectWildcardExtends();

   boolean isObjectWildcardSuper();

   boolean isObject();

   boolean isClassOrInterface();

   boolean isArray();

   int getDimensions();

   IJavaType getArrayElementType();

   IJavaType getArrayTypeDimensionBelow();

   String getName();

   String getSignature();

   boolean isJavaLangObject();

   boolean isJavaLangClass();

   boolean isJavaLangString();

   boolean isFloatingPointType();

   int getEncodingBitsize();

   boolean isSingleSlot();

   boolean isDoubleSlot();

   boolean isLegal();

   boolean isUndefined();

   boolean isDefined();

   boolean isDetermined();

   boolean isWildcard();

   IJavaType asWildcardSup();

   IJavaType asWildcardExt();

   int getSlotCount();

   boolean isSmallInt();

   boolean isSpecificInteger();

   boolean isGenericInteger();

   boolean compareSimple(IJavaType var1);

   boolean canSetType(IJavaType var1, boolean var2);

   IJavaType compareAndGetMostPrecise(IJavaType var1);

   boolean isValidForRendering();

   void format(DFormattingContext var1);

   void formatArray(DFormattingContext var1, IDExpression var2);
}
