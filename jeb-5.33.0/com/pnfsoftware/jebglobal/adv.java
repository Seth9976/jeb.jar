package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public abstract class adv extends adn implements ICConstant {
   @SerId(1)
   protected ICType pC;
   @SerId(2)
   protected Object A;
   @SerId(3)
   String kS;

   protected adv(ICType var1, Object var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public ICType getType() {
      return this.pC;
   }

   @Override
   public Object getValue() {
      return this.A;
   }

   @Override
   public List getSubElements() {
      return afm.pC();
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   protected abstract void kS(COutputSink var1);

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      this.kS(var1);
      this.A(var1);
   }

   protected void pC(COutputSink var1, String var2, ItemClassIdentifiers var3, long var4) {
      if (var1.getSourceCustomizer() != null) {
         String var6 = var1.getSourceCustomizer().customizeRenderedConstant(this, var2);
         if (var6 != null) {
            var2 = var6;
         }
      }

      var1.appendAndRecord(var2, var3, var4);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Constant;
   }

   public adv A() {
      return this;
   }

   @Override
   protected void pC(adl var1) {
      super.pC(var1);
      ((adv)var1).pC = this.pC;
      ((adv)var1).A = this.A;
      ((adv)var1).kS = this.kS;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      return CUtil.getConstantAsLong(this);
   }

   public static int pC(long var0, boolean var2) {
      return adj.pC(var0, var2);
   }

   @Override
   public void setOrigin(String var1) {
      this.kS = var1;
   }

   @Override
   public String getOrigin() {
      return this.kS;
   }

   @Override
   public String toString() {
      return this.getValue() + "";
   }
}
