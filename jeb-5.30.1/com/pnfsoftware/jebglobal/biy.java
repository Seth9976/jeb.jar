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

class biy implements IDexItemToAnchor {
   private List q = new ArrayList();
   private Map RF = new HashMap();

   public biy() {
   }

   public void q(long var1) {
      this.q.add(null);
      this.RF.put(null, var1);
   }

   public void q(long var1, IDexClass var3) {
      ClassCoordinates var4 = new ClassCoordinates(var3.getIndex());
      this.q.add(var4);
      this.RF.put(var4, var1);
   }

   public void q(long var1, IDexField var3) {
      FieldCoordinates var4 = new FieldCoordinates(var3.getIndex());
      this.q.add(var4);
      this.RF.put(var4, var1);
   }

   public void q(long var1, IDexMethod var3) {
      MethodCoordinates var4 = new MethodCoordinates(var3.getIndex());
      this.q.add(var4);
      this.RF.put(var4, var1);
   }

   public void q(long var1, IDexMethod var3, BasicBlock var4) {
      InstructionCoordinates var5 = new InstructionCoordinates(var3.getIndex(), (int)var4.getFirstAddress());
      this.q.add(var5);
      this.RF.put(var5, var1);
   }

   public void q() {
      this.q.clear();
      this.RF.clear();
   }

   @Override
   public int size() {
      return this.q.size();
   }

   @Override
   public Long fromClass(IDexClass var1) {
      return (Long)this.RF.get(new ClassCoordinates(var1.getIndex()));
   }

   @Override
   public Long fromField(IDexField var1) {
      return (Long)this.RF.get(new FieldCoordinates(var1.getIndex()));
   }

   @Override
   public Long fromMethod(IDexMethod var1) {
      return (Long)this.RF.get(new MethodCoordinates(var1.getIndex()));
   }

   @Override
   public Long fromInstruction(IDexMethod var1, BasicBlock var2) {
      return (Long)this.RF.get(new InstructionCoordinates(var1.getIndex(), (int)var2.getFirstAddress()));
   }

   @Override
   public ICodeCoordinates get(long var1) {
      return (ICodeCoordinates)this.q.get((int)var1);
   }

   @Override
   public Long from(ICodeCoordinates var1) {
      return (Long)this.RF.get(var1);
   }
}
