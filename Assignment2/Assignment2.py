'''
Nicholas Bonat Assignment 2
'''
import sys

def create_graph(V):
    size = V
    g = {}

    #create empty dic the size of V
    for i in range(1, size + 1):
        g[i] = {}

    return g

def add_edge(g, v1, v2, weight):
    # add the weight from v1 to v2
    g[v1][v2] = weight

def longest_path(g,size):
    dag = {}

    #initalize the dag
    for i in range(1, size + 1):
        dag[i] = [0, 0]

    visited_node = []
    dag[1][1] = 1

    for j in range(size):
        v = j + 1
        for u, weight in g[v].items():
            if u not in visited_node:
                visited_node.append(u)
            if dag[v][0] + weight > dag[u][0]:
                dag[u][1] = 0
                dag[u][0] = dag[v][0] + weight
                dag[u][1] += dag[v][1]
            elif dag[v][0] + weight == dag[u][0]:
                dag[u][1] += dag[v][1]
    return dag

def main():
    file = sys.stdin
    V, E = (file.readline().strip().split(" "))
    size = int(V)
    edges = int(E)
    g = create_graph(size)

    for x in range(edges):
        in_data = file.readline().strip().split()
        v1 = int(in_data[0])
        v2 = int(in_data[1])
        weight = int(in_data[2])
        # add the nodes and weight to g
        add_edge(g,v1, v2, weight)

    dag_lp = longest_path(g, size)
    print("longest path: {}".format(dag_lp[size][0]))
    print("number of longest paths: {}".format(dag_lp[size][1]))

if __name__ == "__main__":
    main()