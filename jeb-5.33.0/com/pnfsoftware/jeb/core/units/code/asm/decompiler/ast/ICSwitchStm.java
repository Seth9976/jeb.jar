package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Ser
public interface ICSwitchStm extends ICConditionalStatement, ICGenericBreakable {
   ICExpression getSwitchedExpression();

   void setSwitchedExpression(ICExpression var1);

   Set getCaseKeys();

   List getCaseBodies();

   ICBlock getCaseBody(BigInteger var1);

   List getKeysForBlock(ICBlock var1);

   default void addCase(long var1, long var3, long var5, ICBlock var7) {
      this.addCase(Arrays.asList(BigInteger.valueOf(var1), BigInteger.valueOf(var3), BigInteger.valueOf(var5)), var7);
   }

   default void addCase(long var1, long var3, ICBlock var5) {
      this.addCase(Arrays.asList(BigInteger.valueOf(var1), BigInteger.valueOf(var3)), var5);
   }

   default void addCase(long var1, ICBlock var3) {
      this.addCase(BigInteger.valueOf(var1), var3);
   }

   default void addCase(BigInteger var1, ICBlock var2) {
      this.addCase(Arrays.asList(var1), var2);
   }

   void addCase(Collection var1, ICBlock var2);

   Collection removeCasesFromBlock(ICBlock var1, Collection var2);

   ICSwitchStm duplicate();
}
