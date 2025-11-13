package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaNew extends IJavaExpression, IJavaStatement {
   IJavaType getType();

   String getConstructorSignature();

   String getConstructorName();

   IJavaMethod getConstructor();

   List getArguments();

   IJavaClass getGeneratedAnonymousClass(JavaOutputSink var1, int[] var2);

   IJavaNew duplicate();
}
