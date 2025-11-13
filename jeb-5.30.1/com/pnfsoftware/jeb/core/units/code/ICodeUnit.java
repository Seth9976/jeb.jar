package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICodeUnit extends IInteractiveUnit {
   ICodeHierarchy getHierarchy();

   List getStrings();

   List getPackages();

   List getTypes();

   List getClasses();

   List getFields();

   List getMethods();

   ICodeClass getClass(String var1);

   ICodeField getField(String var1);

   ICodeMethod getMethod(String var1);

   ICodeItem getCodeItemByAddress(String var1);

   ICodeCoordinates getCodeCoordinatesFromAddress(String var1);

   String getAddressFromCodeCoordinates(ICodeCoordinates var1);

   CodeCommentManager getCommentManager();

   ITextDocument getDisassemblyDocument();

   String getDisassembly();
}
