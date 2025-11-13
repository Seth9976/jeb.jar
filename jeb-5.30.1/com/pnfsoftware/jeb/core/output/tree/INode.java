package com.pnfsoftware.jeb.core.output.tree;

import com.pnfsoftware.jeb.core.output.IItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface INode extends IItem {
   List getChildren();

   String getLabel();

   String[] getAdditionalLabels();
}
