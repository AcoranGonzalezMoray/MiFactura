package Vista;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import Modelo.Documentos;

class MyDragDropListener implements DropTargetListener {
	private Documentos D;
	public MyDragDropListener(Documentos D){this.D=D;}
    @Override
    public void drop(DropTargetDropEvent event) {
        event.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable transferable = event.getTransferable();
        DataFlavor[] flavors = transferable.getTransferDataFlavors();
        for (DataFlavor flavor : flavors) {
            try {
                if (flavor.isFlavorJavaFileListType()) {
                    List<File> files = (List) transferable.getTransferData(flavor);
                    for (int i=0; i<files.size();i++) {
                    	File f = files.get(i);
                        // Print out the file path
                        // System.out.println("File path is '" + f.getPath() + "'.");
                        D.add(f.getPath());
                    }
                    
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        
        event.dropComplete(true);

    }
    
    public Documentos R() {return D;}
    @Override
    public void dragEnter(DropTargetDragEvent event) {}
    @Override
    public void dragExit(DropTargetEvent event) {}
    @Override
    public void dragOver(DropTargetDragEvent event) {}
    @Override
    public void dropActionChanged(DropTargetDragEvent event) {}

}