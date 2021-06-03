package view.output;

import misc.IDescriptor;
import model.containers.CompositeContainerHead;

public interface IOutput extends IDescriptor{

	public void output(CompositeContainerHead container);
}
