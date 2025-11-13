package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IStructureType extends INativeType {
   boolean isStructure();

   boolean isUnion();

   int getPadding();

   int getAlignment();

   int getFieldsCount();

   List getFields();

   int getIndexOfField(IStructureTypeField var1);

   List getFieldsWithGaps();

   IStructureTypeField getField(int var1);

   IStructureTypeField getFieldByName(String var1);

   IStructureTypeField getFieldAt(int var1);

   IStructureTypeField getFieldAt(int var1, int var2);

   IStructureTypeField getFieldOver(int var1);

   IStructureTypeField getFieldAfter(int var1);

   boolean isCircular(INativeType var1);
}
