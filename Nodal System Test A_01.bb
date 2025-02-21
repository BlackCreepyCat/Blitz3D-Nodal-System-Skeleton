; Definition of a type for a node with Float type inputs
Type Node
    Field id%
    Field input1#   ; Input of type Float
    Field input2#   ; Input of type Float
    Field nextNode.Node  ; To link nodes together
End Type

; Function to create a node
Function CreateNode.Node(id, input1#, input2#)
    Local node.Node = New Node
    node\id = id
    node\input1# = input1#
    node\input2# = input2#
    node\nextNode = Null
    Return node
End Function

; Function to link a node to another
Function LinkNodes(node1.Node, node2.Node)
    node1\nextNode = node2
End Function

; Function to display the nodes
Function DisplayNodes(node.Node)
    Local currentNode.Node = node  ; Start with the first node
    
    While currentNode <> Null
        Print "Node: " + currentNode\id + " / Input1: " + Str(currentNode\input1#) + " / Input2: " + Str(currentNode\input2#)        
        currentNode = currentNode\nextNode  ; Move to the next node
    Wend
End Function

; Function to add the inputs of the nodes
Function NodeFunctionAdd#(node.Node)
    Local currentNode.Node = node  ; Start with the first node
	
    Local OutValue# = 0
    
    While currentNode <> Null
        OutValue# = OutValue# + (currentNode\input1# + currentNode\input2#)
        currentNode = currentNode\nextNode  ; Move to the next node
    Wend
	
    Return OutValue#
End Function

; Function to subtract the inputs of the nodes
Function NodeFunctionSub#(node.Node)
    Local currentNode.Node = node  ; Start with the first node
	
    Local OutValue# = 0
    
    While currentNode <> Null
        OutValue# = OutValue# + (currentNode\input1# - currentNode\input2#)
        currentNode = currentNode\nextNode  ; Move to the next node
    Wend
	
    Return OutValue#
End Function

; Function to divide the inputs of the nodes
Function NodeFunctionDiv#(node.Node)
    Local currentNode.Node = node  ; Start with the first node
	
    Local OutValue# = 1  ; Initialize with 1 for division
    
    While currentNode <> Null
        If currentNode\input2# <> 0  ; To avoid division by zero
            OutValue# = OutValue# * (currentNode\input1# / currentNode\input2#)
        Else
            Print "Error: Division by zero at Node " + Str(currentNode\id)
            Return 0  ; Return 0 if division by zero
        EndIf
        currentNode = currentNode\nextNode  ; Move to the next node
    Wend
	
    Return OutValue#
End Function

Graphics3D 800,600,32,2

; Example of usage
Local node1.Node = CreateNode(1 , 2 , 2)
Local node2.Node = CreateNode(2 , 10 , 10)
LinkNodes(node1, node2)

Local node10.Node = CreateNode(3, 2 , 2)
LinkNodes(node10, node1)

; Display nodes and operation results
Print "Display of node10 and its chain (linked to node1):"
DisplayNodes(node10)
Print ""
Print "Addition result of node10 chain: " + Str(NodeFunctionAdd#(node10))
Print "Subtraction result of node10 chain: " + Str(NodeFunctionSub#(node10))
Print "Division result of node10 chain: " + Str(NodeFunctionDiv#(node10))  ; Display division
Print ""

Print "Display of node1 and its chain (linked to node2):"
DisplayNodes(node1)
Print ""
Print "Addition result of node1 chain: " + Str(NodeFunctionAdd#(node1))
Print "Subtraction result of node1 chain: " + Str(NodeFunctionSub#(node1))
Print "Division result of node1 chain: " + Str(NodeFunctionDiv#(node1))  ; Display division
Print ""

Print "Display of node2 (isolated):"
DisplayNodes(node2)
Print ""
Print "Addition result of node2: " + Str(NodeFunctionAdd#(node2))
Print "Subtraction result of node2: " + Str(NodeFunctionSub#(node2))
Print "Division result of node2: " + Str(NodeFunctionDiv#(node2))  ; Display division
Print ""

WaitKey

;~IDEal Editor Parameters:
;~C#Blitz3D