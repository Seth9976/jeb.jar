package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.CodeConstantManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeLibrary;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryMetadata;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.base.ISimpleFilter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class bck implements ITypeLibrary {
   @SerId(1)
   TypeLibraryMetadata q;
   @SerId(2)
   ITypeManager RF;
   @SerId(3)
   List xK;
   @SerId(4)
   CodeConstantManager Dw;

   public bck(TypeLibraryMetadata var1, ITypeManager var2) {
      this(var1, var2, null, null);
   }

   public bck(TypeLibraryMetadata var1, ITypeManager var2, List var3, CodeConstantManager var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   @Override
   public ProcessorType getPrimaryProcessorType() {
      return this.q.getPrimaryProcessorType();
   }

   @Override
   public List getProcessorTypes() {
      return this.q.getProcessorTypes();
   }

   @Override
   public SubsystemType getPrimarySubsystemType() {
      return this.q.getPrimarySubsystemType();
   }

   @Override
   public List getSubsystemTypes() {
      return this.q.getSubsystemTypes();
   }

   @Override
   public CompilerType getCompilerType() {
      return this.q.getCompilerType();
   }

   @Override
   public int getGroupId() {
      return this.q.getGroupId();
   }

   @Override
   public double getPriorityOrder() {
      return this.q.getPriorityOrder();
   }

   @Override
   public int getUuid() {
      return this.q.getUuid();
   }

   @Override
   public int getVersion() {
      return this.q.getVersion();
   }

   @Override
   public long getCreationTimestamp() {
      return this.q.getCreationTimestamp();
   }

   @Override
   public String getName() {
      return this.q.getName();
   }

   @Override
   public String getDescription() {
      return this.q.getDescription();
   }

   @Override
   public String getAuthor() {
      return this.q.getAuthor();
   }

   public ITypeManager q() {
      return this.RF;
   }

   @Override
   public Collection getTypes() {
      return this.RF.getTypes();
   }

   @Override
   public Collection getTypes(ISimpleFilter var1) {
      return this.RF.getTypes(var1);
   }

   @Override
   public Collection getRoutines() {
      return this.xK == null ? Collections.emptyList() : this.xK;
   }

   @Override
   public CodeConstantManager getConstantManager() {
      return this.Dw == null ? CodeConstantManager.EMPTY : this.Dw;
   }

   @Override
   public String toString() {
      return Strings.ff("%s (typeman:%s, routines:%d)", this.q, this.RF, this.getRoutines().size());
   }
}
