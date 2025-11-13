package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugLine;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.List;

@Ser
public class bet implements IDexDebugLine {
   @SerId(1)
   public int pC;
   @SerId(2)
   public List A;
   @SerId(3)
   public List kS;
   @SerId(4)
   public List wS;
   @SerId(5)
   public boolean UT;
   @SerId(6)
   public boolean E;
   @SerId(7)
   public int sY;

   @Override
   public int getLineNumber() {
      return this.pC;
   }

   @Override
   public List getVariables() {
      return this.A == null ? Collections.emptyList() : Collections.unmodifiableList(this.A);
   }

   @Override
   public List getVariablesEnd() {
      return this.kS == null ? Collections.emptyList() : Collections.unmodifiableList(this.kS);
   }

   @Override
   public List getVariablesRestart() {
      return this.wS == null ? Collections.emptyList() : Collections.unmodifiableList(this.wS);
   }

   @Override
   public boolean isPrologueEnd() {
      return this.UT;
   }

   @Override
   public boolean isEpilogueBegin() {
      return this.E;
   }

   @Override
   public int getSourceIndex() {
      return this.sY;
   }
}
