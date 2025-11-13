package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICCall extends ICExpression, ICStatement {
   String getMethodAddress();

   ICMethod getMethod();

   ICExpression getCallsite();

   List getCandidateMethodAddresses();

   List getCandidateMethods();

   boolean isStatic();

   boolean isSuperCall();

   boolean isNonReturningCall();

   boolean isBadCall();

   int getArgumentCount();

   List getArguments();

   ICCall duplicate();
}
