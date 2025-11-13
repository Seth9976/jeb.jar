package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugLine;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.List;

@Ser
public class bin implements IDexDebugLine {
   @SerId(1)
   public int q;
   @SerId(2)
   public List RF;
   @SerId(3)
   public List xK;
   @SerId(4)
   public List Dw;
   @SerId(5)
   public boolean Uv;
   @SerId(6)
   public boolean oW;
   @SerId(7)
   public int gO;

   @Override
   public int getLineNumber() {
      return this.q;
   }

   @Override
   public List getVariables() {
      return this.RF == null ? Collections.emptyList() : Collections.unmodifiableList(this.RF);
   }

   @Override
   public List getVariablesEnd() {
      return this.xK == null ? Collections.emptyList() : Collections.unmodifiableList(this.xK);
   }

   @Override
   public List getVariablesRestart() {
      return this.Dw == null ? Collections.emptyList() : Collections.unmodifiableList(this.Dw);
   }

   @Override
   public boolean isPrologueEnd() {
      return this.Uv;
   }

   @Override
   public boolean isEpilogueBegin() {
      return this.oW;
   }

   @Override
   public int getSourceIndex() {
      return this.gO;
   }
}
