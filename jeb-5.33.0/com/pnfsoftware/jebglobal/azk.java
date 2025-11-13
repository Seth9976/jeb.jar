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
public class azk implements ITypeLibrary {
   @SerId(1)
   TypeLibraryMetadata pC;
   @SerId(2)
   ITypeManager A;
   @SerId(3)
   List kS;
   @SerId(4)
   CodeConstantManager wS;

   public azk(TypeLibraryMetadata var1, ITypeManager var2) {
      this(var1, var2, null, null);
   }

   public azk(TypeLibraryMetadata var1, ITypeManager var2, List var3, CodeConstantManager var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   @Override
   public ProcessorType getPrimaryProcessorType() {
      return this.pC.getPrimaryProcessorType();
   }

   @Override
   public List getProcessorTypes() {
      return this.pC.getProcessorTypes();
   }

   @Override
   public SubsystemType getPrimarySubsystemType() {
      return this.pC.getPrimarySubsystemType();
   }

   @Override
   public List getSubsystemTypes() {
      return this.pC.getSubsystemTypes();
   }

   @Override
   public CompilerType getCompilerType() {
      return this.pC.getCompilerType();
   }

   @Override
   public int getGroupId() {
      return this.pC.getGroupId();
   }

   @Override
   public double getPriorityOrder() {
      return this.pC.getPriorityOrder();
   }

   @Override
   public int getUuid() {
      return this.pC.getUuid();
   }

   @Override
   public int getVersion() {
      return this.pC.getVersion();
   }

   @Override
   public long getCreationTimestamp() {
      return this.pC.getCreationTimestamp();
   }

   @Override
   public String getName() {
      return this.pC.getName();
   }

   @Override
   public String getDescription() {
      return this.pC.getDescription();
   }

   @Override
   public String getAuthor() {
      return this.pC.getAuthor();
   }

   @Override
   public Collection getTypes() {
      return this.A.getTypes();
   }

   @Override
   public Collection getTypes(ISimpleFilter var1) {
      return this.A.getTypes(var1);
   }

   @Override
   public Collection getRoutines() {
      return this.kS == null ? Collections.emptyList() : this.kS;
   }

   @Override
   public CodeConstantManager getConstantManager() {
      return this.wS == null ? CodeConstantManager.EMPTY : this.wS;
   }

   @Override
   public String toString() {
      return Strings.ff("%s (typeman:%s, routines:%d)", this.pC, this.A, this.getRoutines().size());
   }
}
