package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.render.IDexItemToAnchor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class bfd implements IDexItemToAnchor {
   private List pC = new ArrayList();
   private Map A = new HashMap();

   public bfd() {
   }

   public void pC(long var1) {
      this.pC.add(null);
      this.A.put(null, var1);
   }

   public void pC(long var1, IDexClass var3) {
      ClassCoordinates var4 = new ClassCoordinates(var3.getIndex());
      this.pC.add(var4);
      this.A.put(var4, var1);
   }

   public void pC(long var1, IDexField var3) {
      FieldCoordinates var4 = new FieldCoordinates(var3.getIndex());
      this.pC.add(var4);
      this.A.put(var4, var1);
   }

   public void pC(long var1, IDexMethod var3) {
      MethodCoordinates var4 = new MethodCoordinates(var3.getIndex());
      this.pC.add(var4);
      this.A.put(var4, var1);
   }

   public void pC(long var1, IDexMethod var3, BasicBlock var4) {
      InstructionCoordinates var5 = new InstructionCoordinates(var3.getIndex(), (int)var4.getFirstAddress());
      this.pC.add(var5);
      this.A.put(var5, var1);
   }

   public void pC() {
      this.pC.clear();
      this.A.clear();
   }

   @Override
   public int size() {
      return this.pC.size();
   }

   @Override
   public Long fromClass(IDexClass var1) {
      return (Long)this.A.get(new ClassCoordinates(var1.getIndex()));
   }

   @Override
   public Long fromField(IDexField var1) {
      return (Long)this.A.get(new FieldCoordinates(var1.getIndex()));
   }

   @Override
   public Long fromMethod(IDexMethod var1) {
      return (Long)this.A.get(new MethodCoordinates(var1.getIndex()));
   }

   @Override
   public Long fromInstruction(IDexMethod var1, BasicBlock var2) {
      return (Long)this.A.get(new InstructionCoordinates(var1.getIndex(), (int)var2.getFirstAddress()));
   }

   @Override
   public ICodeCoordinates get(long var1) {
      return (ICodeCoordinates)this.pC.get((int)var1);
   }

   @Override
   public Long from(ICodeCoordinates var1) {
      return (Long)this.A.get(var1);
   }
}
