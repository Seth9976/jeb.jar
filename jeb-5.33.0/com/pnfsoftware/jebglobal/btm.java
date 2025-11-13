package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

public class btm implements IDEmuContext {
   private String pC;
   private Deque A = new ArrayDeque();
   private Deque kS = new ArrayDeque();

   btm(String var1) {
      this.pC = var1;
   }

   @Override
   public IDEmuContext copy() {
      btm var1 = new btm(this.pC);
      var1.A = new ArrayDeque(this.A.size());
      this.A.forEach(var1x -> var1.A.add(var1x.copy()));
      var1.kS = new ArrayDeque(this.kS);
      return var1;
   }

   @Override
   public String getName() {
      return this.pC;
   }

   @Override
   public int getCountOfFrames() {
      return this.A.size();
   }

   @Override
   public IDEmuFrame getFrame(int var1) {
      return (IDEmuFrame)new ArrayList(this.A).get(var1);
   }

   public void pC(IDEmuFrame var1) {
      this.A.push(var1);
   }

   public IDEmuFrame pC() {
      return (IDEmuFrame)this.A.pop();
   }

   public IDEmuFrame A() {
      return (IDEmuFrame)this.A.peek();
   }

   @Override
   public boolean hasFrames() {
      return !this.A.isEmpty();
   }

   @Override
   public Collection getFrames() {
      return Collections.unmodifiableCollection(this.A);
   }

   @Override
   public Collection getOrigins() {
      return Collections.unmodifiableCollection(this.kS);
   }

   @Override
   public void pushOriginInfo(String var1) {
      this.kS.push(var1);
   }

   @Override
   public String toString() {
      return this.pC;
   }
}
