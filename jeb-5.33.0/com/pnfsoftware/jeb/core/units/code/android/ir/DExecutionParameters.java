package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DExecutionParameters {
   SortedMap insnmap;
   Map dalvik2irmap;
   Map ir2dalvikmap;
   CFG cfg;
   IDTryData exdata;
   Map initvals;
   public Integer pc;
   public Integer pcExpectedTermination;
   public Integer pcThresholdMin;
   public Integer pcThresholdMax;
   public Integer iterationCountLeft;

   public DExecutionParameters(SortedMap var1) {
      this(var1, null, null);
   }

   public DExecutionParameters(SortedMap var1, Map var2, Map var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.insnmap = var1;
         this.dalvik2irmap = var2;
         this.ir2dalvikmap = var3;
      }
   }

   public DExecutionParameters(CFG var1) {
      TreeMap var2 = new TreeMap();
      var1.getInstructions().forEach(var1x -> var2.put((int)var1x.getOffset(), var1x));
      this.insnmap = var2;
      HashMap var3 = new HashMap();

      for (IDInstruction var5 : var1.instructions()) {
         var3.put(var5.getPhysicalOffset(), (int)var5.getOffset());
      }

      this.dalvik2irmap = var3;
   }

   public DExecutionParameters(IDMethodContext var1) {
      this(var1.getCfg(), var1.getExceptionData());
   }

   public DExecutionParameters(CFG var1, IDTryData var2) {
      TreeMap var3 = new TreeMap();
      var1.getInstructions().forEach(var1x -> var3.put((int)var1x.getOffset(), var1x));
      this.insnmap = var3;
      this.dalvik2irmap = null;
      this.cfg = var1;
      this.exdata = var2;
   }

   public Map getInstructionMap() {
      return this.insnmap;
   }

   public Map getDalvikToIRMap() {
      return this.dalvik2irmap;
   }

   public Map getIRToDalvikMap() {
      return this.ir2dalvikmap;
   }

   public CFG getCFG() {
      return this.cfg;
   }

   public IDTryData getExceptionData() {
      return this.exdata;
   }

   public Map getInitialValues() {
      return this.initvals == null ? Collections.emptyMap() : Collections.unmodifiableMap(this.initvals);
   }

   public void addInitialValue(int var1, IDImm var2) {
      if (this.initvals == null) {
         this.initvals = new HashMap();
      }

      this.initvals.put(var1, var2);
   }

   public void prepareIterations(Integer var1) {
      this.initvals = null;
      this.pc = null;
      this.pcExpectedTermination = null;
      this.pcThresholdMin = null;
      this.pcThresholdMax = null;
      this.iterationCountLeft = var1;
   }
}
